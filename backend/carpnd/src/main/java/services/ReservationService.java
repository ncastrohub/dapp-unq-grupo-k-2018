package services;

import model.Reservation;
import repositories.ReservationRespository;
import utils.ListAndTotal;
import utils.OwnPaginationPage;

public class ReservationService extends GenericService<Reservation> {


    public OwnPaginationPage<Reservation> getPaginationPageOwner(Long ownerID, Integer pageNumber){
        ListAndTotal<Reservation> result = this.findForOwner(ownerID, pageNumber);

        OwnPaginationPage<Reservation> page = new OwnPaginationPage<>();
        page.setElementList(result.elementList);
        if (pageNumber > 0) {
            page.beforeUrl = "reservation/list/owner/" + (pageNumber - 1);
        }
        Integer total =  result.total;
        Integer currentPage = pageNumber + 1;
        if (total - (currentPage * 4) > 0) {
            page.nextUrl = "reservation/list/owner/" + (pageNumber + 1);
        }
        return page;
    }

    public ListAndTotal<Reservation> findForOwner(Long ownerID, Integer pageNumber){
        return ((ReservationRespository) this.getRepository()).findForOwner(ownerID, pageNumber);
    }

    public ListAndTotal<Reservation> findForCustomer(Long ownerID, Integer pageNumber){
        return ((ReservationRespository) this.getRepository()).findForCustomer(ownerID, pageNumber);
    }

    public OwnPaginationPage<Reservation> getPaginationPageOwner(Long ownerID){
        return this.getPaginationPageOwner(ownerID, 0);
    }

    public OwnPaginationPage<Reservation> getPaginationPageCustomer(Long customerID){
        return this.getPaginationPageCustomer(customerID, 0);
    }

    public OwnPaginationPage<Reservation> getPaginationPageCustomer(Long customerID, Integer pageNumber){
        ListAndTotal<Reservation> result = this.findForCustomer(customerID, pageNumber);

        OwnPaginationPage<Reservation> page = new OwnPaginationPage<>();
        page.setElementList(result.elementList);
        if (pageNumber > 0) {
            page.beforeUrl = "reservation/list/owner/" + (pageNumber - 1);
        }
        Integer total =  result.total;
        Integer currentPage = pageNumber + 1;
        if (total - (currentPage * 4) > 0) {
            page.nextUrl = "reservation/list/owner/" + (pageNumber + 1);
        }
        return page;
    }
}
