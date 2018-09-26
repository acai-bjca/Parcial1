package edu.eci.pdsw.validator;

import java.util.Optional;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Utility class to validate an employee's salary
 */
public class SalaryValidator implements EmployeeValidator {
	/**
	 * {@inheritDoc}}
	 */
	public Optional<ErrorType> validate(Employee employee) {
		Optional<ErrorType> optional = Optional.ofNullable(null);
        if(employee.getPersonId()<=1000 || employee.getPersonId()>=100000) {
        	optional = Optional.of(ErrorType.INVALID_ID);
        }else if(employee.getSocialSecurityType() == SocialSecurityType.SISBEN && (employee.getSalary()<100 || employee.getSalary()>=1500)) {
        	optional = Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION);
        }else if(employee.getSocialSecurityType() == SocialSecurityType.EPS && (employee.getSalary()<1500 || employee.getSalary()>=10000)) {
        	optional = Optional.of(ErrorType.INVALID_EPS_AFFILIATION);	
        }else if(employee.getSocialSecurityType() == SocialSecurityType.PREPAID && (employee.getSalary()<10000 || employee.getSalary()>=50000)) {
        	optional = Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION);
        }else {
        	optional = Optional.empty();
        }	
        return optional;
	}
}
