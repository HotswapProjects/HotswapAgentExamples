package org.hotswap.agent.example.springboot;

import org.hotswap.agent.plugin.hotswapper.HotSwapper;
import org.hotswap.agent.plugin.spring.scanner.ClassPathBeanDefinitionScannerAgent;
import org.hotswap.agent.util.test.WaitHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicSpringBootTests {
	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		assertNotNull(applicationContext);
	}

	@Test
	public void addMethodDidNotChangeSpringBeanNames() throws Exception {
		TestPojo test = applicationContext.getAutowireCapableBeanFactory().createBean(TestPojo.class);
		assertEquals(0, applicationContext.getBeanNamesForType(TestPojo.class).length);

		swapClasses(TestPojo.class, TestPojo2.class);


		assertEquals(0, applicationContext.getBeanNamesForType(TestPojo.class).length);
	}


	public static void swapClasses(Class original, Class swap) throws Exception {
		ClassPathBeanDefinitionScannerAgent.reloadFlag = true;
		HotSwapper.swapClasses(original, swap.getName());
		assertTrue(WaitHelper.waitForCommand(new WaitHelper.Command() {
			@Override
			public boolean result() throws Exception {
				return !ClassPathBeanDefinitionScannerAgent.reloadFlag;
			}
		}));
	}
}
