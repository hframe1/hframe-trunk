package com.hframework.ext.activiti.handler;

import com.hframework.base.bean.AuthContext;
import com.hframework.base.service.AuthService;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ResourceWrapper;
import com.hframework.controller.AuthServiceProxy;
import com.hframework.web.SessionKey;
import com.hframework.web.bean.DataSetDescriptor;
import com.hframework.web.bean.WebContext;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.explorer.Constants;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.identity.LoggedInUser;
import org.activiti.explorer.ui.login.LoginHandler;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangquanhong on 2017/1/22.
 */
public class ActivitiLoginHandler implements LoginHandler {

    private  String autoServiceClassPath = PropertyConfigurerUtils.getProperty("hframe.auth.service.impl");

    public LoggedInUser authenticate(String userName, String password) {
//        LoggedInUserImpl loggedInUser = null;
//
//        try {
//
//
//            if (identityService.checkPassword(userName, password)) {
//                User user = identityService.createUserQuery().userId(userName).singleResult();
//                // Fetch and cache user data
//                loggedInUser = new LoggedInUserImpl(user, password);
//                List<Group> groups = identityService.createGroupQuery().groupMember(user.getId()).list();
//                for (Group group : groups) {
//
//                    if (Constants.SECURITY_ROLE.equals(group.getType())) {
//                        loggedInUser.addSecurityRoleGroup(group);
//                        if (Constants.SECURITY_ROLE_USER.equals(group.getId())) {
//                            loggedInUser.setUser(true);
//                        }
//                        if (Constants.SECURITY_ROLE_ADMIN.equals(group.getId())) {
//                            loggedInUser.setAdmin(true);
//                        }
//                    } else if (ExplorerApp.get().getAdminGroups() != null
//                            && ExplorerApp.get().getAdminGroups().contains(group.getId())) {
//                        loggedInUser.addSecurityRoleGroup(group);
//                        loggedInUser.setAdmin(true);
//                    } else if (ExplorerApp.get().getUserGroups() != null
//                            && ExplorerApp.get().getUserGroups().contains(group.getId())) {
//                        loggedInUser.addSecurityRoleGroup(group);
//                        loggedInUser.setUser(true);
//                    } else {
//                        loggedInUser.addGroup(group);
//                    }
//
//
//                }
//            }
//        } catch (Exception e) {
//            // Do nothing, returning null should be enough
//        }
        return null;
    }

    public LoggedInUser authenticate(HttpServletRequest request, HttpServletResponse response) {
        try {
            AuthService authServiceProxy = (AuthService) ServiceFactory.getService(Class.forName(autoServiceClassPath));
            AuthContext authContext = authServiceProxy.getAuthContext(request);
            Object userObject = authContext.getUser().userObject;
            if(authContext != null && ExplorerApp.get().getLoggedInUser() == null) {
                DataSetDescriptor dataSet = WebContext.get().getDataSet(userObject.getClass());
                String userId = BeanUtils.getProperty(userObject, ResourceWrapper.JavaUtil.getJavaVarName(dataSet.getKeyField().getCode()));
                String userName = BeanUtils.getProperty(userObject, ResourceWrapper.JavaUtil.getJavaVarName(dataSet.getNameField().getCode()));
                return createActivitiUser(userId,userName, authContext.getAuthRoleManager().keySet(), authContext.getAuthRoleManager().getRoleIdNameMap());
            }
        }catch (Exception e) {
        }

        return null;
    }

    public void logout(LoggedInUser userTologout) {
        Authentication.setAuthenticatedUserId(null);
    }


    public void onRequestStart(HttpServletRequest request, HttpServletResponse response) {
        if (ExplorerApp.get().getLoggedInUser() != null && request.getSession(false) != null) {
            request.getSession().setAttribute(Constants.AUTHENTICATED_USER_ID, ExplorerApp.get().getLoggedInUser().getId());
        }

    }

    private LoggedInUser createActivitiUser(final String userId, final String userName, final Set<String> roleIds, final Map<String, String> roleIdNameMap) {
        LoggedInUser user = new LoggedInUser() {
            public String getId() {
                return userId;
            }

            public String getFirstName() {
                return "";
            }

            public String getLastName() {
                return userName;
            }

            public String getFullName() {
                return userName;
            }

            public String getPassword() {
                return userName;
            }

            public boolean isAdmin() {
                return false;
            }

            public boolean isUser() {
                return true;
            }

            public List<Group> getSecurityRoles() {
                return null;
            }

            public List<Group> getGroups() {
                List<Group> groups = new ArrayList<Group>();
                for (final String roleId : roleIds) {
                    groups.add(new Group() {
                        public String getId() {
                            return roleId;
                        }

                        public void setId(String id) {

                        }

                        public String getName() {
                            return roleIdNameMap.get(roleId);
                        }

                        public void setName(String name) {

                        }

                        public String getType() {
                            return null;
                        }

                        public void setType(String string) {

                        }
                    });
                }
                return groups;
            }
        };

        return user;
    }


    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response) {

    }
}
