package byteCode;

import static net.bytebuddy.matcher.ElementMatchers.*;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

public class MagicianAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		new AgentBuilder.Default()
			.type(ElementMatchers.any())
			.transform((builder, typeDescription, classLoader, javaModule, protectionDomain)
				-> builder.method(named("pullOut")).intercept(FixedValue.value("Cat!"))).installOn(inst);
	}
}
