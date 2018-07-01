package services;

import model.Publication;
import model.VehicleType;
import org.springframework.transaction.annotation.Transactional;
import repositories.PublicationRespository;
import utils.ListAndTotal;
import utils.OwnPaginationPage;

public class PublicationService extends GenericService<Publication> {


    @Transactional(readOnly = true)
    public ListAndTotal<Publication> findByVehicleType(VehicleType vehicleType, Integer pageNumber) {
        return ((PublicationRespository) this.getRepository()).findByVehicleType(vehicleType, pageNumber);
    }


    @Transactional(readOnly = true)
    public ListAndTotal<Publication> findByVehicleType(VehicleType vehicleType) {
        return ((PublicationRespository) this.getRepository()).findByVehicleType(vehicleType, 0);
    }


    @Transactional(readOnly = true)
    public ListAndTotal<Publication> findBy(VehicleType vehicleType, Integer pageNumber) {
        return ((PublicationRespository) this.getRepository()).findByVehicleType(vehicleType, pageNumber);
    }


    public OwnPaginationPage<Publication> getPaginationPageByVehicleType(VehicleType vehicleType, Integer pageNumber){
        ListAndTotal<Publication> result = this.findByVehicleType(vehicleType, pageNumber);

        OwnPaginationPage<Publication> page = new OwnPaginationPage<>();
        page.setElementList(result.elementList);
        if (pageNumber > 0) {
            page.beforeUrl = "publication/list/" + vehicleType.toString() + "/" + (pageNumber - 1);
        }
        Integer total =  result.total;
        Integer currentPage = pageNumber + 1;
        if (total - (currentPage * 4) > 0) {
            page.nextUrl = "publication/list/" + vehicleType.toString() + "/" + (pageNumber + 1);
        }
        return page;
    }


    public OwnPaginationPage<Publication> getPaginationPageByVehicleType(VehicleType vehicleType){
        return this.getPaginationPageByVehicleType(vehicleType, 0);
    }



}
