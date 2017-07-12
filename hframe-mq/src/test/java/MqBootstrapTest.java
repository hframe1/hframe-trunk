
import com.hframework.mq.MqBootstrap;
import com.hframework.mq.processor.AbstractMqProcessor;
import com.hframework.mq.processor.MqProcessor;
import com.hframework.mq.sender.MqSender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhangqh6
 * Date: 2016/3/18 17:47:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-config.xml")
public class MqBootstrapTest {

    private MqBootstrap mqBootstrap;

    private MqSender mqSender;

    private static final String defaultExchange = "EXCHANGE_DIRECT_TEST";
    private static final String defaultQueue = "QUEUE_TEST";

    private Person testPerson ;
    {
        testPerson = new Person("张三",12,false);
        testPerson.addFriend(new Person("李四",11,true));
        testPerson.addFriend(new Person("王二",13,false));
    }


    @Before
    public void init() {
        mqBootstrap.start();
    }

    @Test
    public void sendString()  {
        mqSender.send(defaultQueue, defaultExchange, "hello world");
    }

    @Test
    public void sendObject() {
        mqSender.send(defaultQueue, defaultExchange, testPerson);
    }

    @Test
    public void sendTemp() throws  InterruptedException{
        String tempExchange = "EXCHANGE_DIRECT_TEST_TEMP";//以前未声明的exchange
        String tempQueue = "QUEUE_TEST_TEMP";//以前未声明的queue
        mqSender.send(tempQueue, tempExchange, testPerson);
        //发送成功后此时不会接受到消息，还需要绑定对应的消费程序
//        controller.add(tempQueue, tempExchange, new ApiProcessEventProcessor());
    }

    @After
    public void end() throws InterruptedException{
        Thread.sleep(2000);
    }

    class Person {
        private String name;
        private int age;
        private boolean gender;

        private List<Person> friends;

        public Person(){
            friends = new ArrayList<Person>();
        }

        public Person(String name, int age, boolean gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            friends = new ArrayList<Person>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public void addFriend(Person person) {
            friends.add(person);
        }

        public List<Person> getFriends() {
            return friends;
        }

        public void setFriends(List<Person> friends) {
            this.friends = friends;
        }
    }

    class PersonMqProcessor extends AbstractMqProcessor implements MqProcessor {

        @Override
        public void processInternal(Object object) {
            Person person = (Person) object;
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.isGender());
            System.out.println(person.getFriends());

        }
    }
}
