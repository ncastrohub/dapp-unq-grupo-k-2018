package services.Validators;

import model.Vehicle;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VehicleValidator implements Validator {


    /**
     * This Validator validates *just* Person instances
     */
    public boolean supports(Class clazz) {
        return Vehicle.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    /*this.capacity = capacity;
        this.type = type;
        this.description = description;
        this.photo = link;
        this.owner = owner;*/
//
//
//    public void validate(Object obj, Errors e) {
//        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//        ValidationUtils.rejectIfEmpty(e, "capacity", "capacity.empty");
//        ValidationUtils.rejectIfEmpty(e, "type", "capacity.empty");
//        ValidationUtils.rejectIfEmpty(e, "photo", "capacity.empty");
//
//        Vehicle p = (Vehicle) obj;
//        if (p.getCapacity() <= 0) {
//            e.rejectValue("capacity", "valuemustbepositive");
//        } else if (p.getAge() > 110) {
//            e.rejectValue("age", "too.darn.old");
//        }
//    }
}