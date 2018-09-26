package edu.eci.pdsw.validator;

import edu.eci.pdsw.model.SocialSecurityType;
import org.junit.Test;
import static org.quicktheories.QuickTheory.qt;
/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {
	//The class under test.
	private SalaryValidator validator = new SalaryValidator();
	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
        qt()
        .forAll(GeneratorEmployee.employee())
        .check(empleado -> {
            if(validator.validate(empleado).get() == (ErrorType.INVALID_ID)){
                return empleado.getPersonId()<=1000 || empleado.getPersonId()>=100000;
            }else if (validator.validate(empleado).get() == (ErrorType.INVALID_SISBEN_AFFILIATION)) {
                return (empleado.getSocialSecurityType() == SocialSecurityType.SISBEN && (empleado.getSalary()<100 || empleado.getSalary()>=1500));
            }else if(validator.validate(empleado).get() == (ErrorType.INVALID_EPS_AFFILIATION)) {
                return (empleado.getSocialSecurityType() == SocialSecurityType.EPS && (empleado.getSalary()<1500 || empleado.getSalary()>=10000));
            }else if (validator.validate(empleado).get() == (ErrorType.INVALID_PREPAID_AFFILIATION)) {
                return (empleado.getSocialSecurityType() == SocialSecurityType.PREPAID && (empleado.getSalary()<10000 || empleado.getSalary()>=500000));
            }else {return !validator.validate(empleado).isPresent();}
        }); 
        //validator.validate(null);
	}
}
