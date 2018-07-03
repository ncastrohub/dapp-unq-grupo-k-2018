import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { Page } from '../../publication/publication';
import { Reservation } from '../reservation';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {

  errorList = [];
  reservationList: Page<Reservation>;

  constructor(private service: ReservationService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  	let path = this.route.snapshot.routeConfig.path;
  	if (path.includes("owner")){
		this.getReservationListOwner();
  	}else{
  		this.getReservationListCustomer();
  	}
  }

  getReservationListCustomer(){
    this.service.getReservationListCustomer().subscribe(
      data => this.reservationList = data,
      error => this.errorList.push(error)
    );
  }

  getReservationListOwner(){
    this.service.getReservationListOwner().subscribe(
      data => this.reservationList = data,
      error => this.errorList.push(error)
    );
  }

  goToDetails(publicationId:string) {
    this.router.navigate(['/publication/detail/' + publicationId]);
  }

  goUrl(url){
    this.service.getReservationForUrl(url).subscribe(
      data => this.reservationList = data,
      error => this.errorList.push(error)
    );
  }

  show(reservationId){
    this.router.navigate(['/reservation/detail/' + reservationId]);
  }

}
