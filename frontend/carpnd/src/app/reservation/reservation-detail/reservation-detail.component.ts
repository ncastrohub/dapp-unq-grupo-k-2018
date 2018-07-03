import { Component, OnInit } from '@angular/core';
import { Reservation } from '../reservation';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { ReservationService } from '../reservation.service';


@Component({
  selector: 'app-reservation-detail',
  templateUrl: './reservation-detail.component.html',
  styleUrls: ['./reservation-detail.component.css']
})
export class ReservationDetailComponent implements OnInit {

  constructor(private service: ReservationService, private router: Router, private route: ActivatedRoute) { }
  
  reservation: Reservation;
  myEmail = localStorage.getItem('email');
  errorList = [];
  ngOnInit() {
  	let reservationId = this.route.snapshot.paramMap.get('reservationId');
  	this.service.getReservation(reservationId).subscribe(data => {
  		this.reservation = data;
  })
  }


  setState(state){
    this.service.setState(this.reservation.id, state).subscribe(
      data =>  this.reservation = data,
      error => this.errorList.push(error)
    );
  }

}
