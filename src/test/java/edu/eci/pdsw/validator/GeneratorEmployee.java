package edu.eci.pdsw.validator;

import static org.quicktheories.generators.SourceDSL.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;


public class GeneratorEmployee {
	public static Gen<Employee> employee() {
            return genId().zip(genSalary(), genSocialSecurityType(), (id, salary, socialS)-> new Employee(id, salary, socialS));
	}
	
	public static Gen<Integer> genId() {
            return integers().allPositive();
	}
	
	public static Gen<Long> genSalary() {
            return Generate.longRange(0, 100000000);
	}
	
	public static Gen<SocialSecurityType> genSocialSecurityType() {
            return Generate.enumValues(SocialSecurityType.class);
	}

}
