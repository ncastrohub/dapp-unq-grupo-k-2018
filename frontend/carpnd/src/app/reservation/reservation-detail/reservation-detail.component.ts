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
  
  ngOnInit() {
  	let reservationId = this.route.snapshot.paramMap.get('reservationId');
	this.service.getReservation(reservationId).subscribe(data => {
		this.reservation = data;
	})
  
  }

}
