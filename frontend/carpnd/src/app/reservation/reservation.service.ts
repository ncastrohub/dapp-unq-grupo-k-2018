import { Injectable } from '@angular/core';
// import { Vehicle } from '../vehicle';
import { Observable ,  BehaviorSubject } from 'rxjs';
import { AppConfig } from '../config';
import { Reservation } from './reservation';
// import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { Vehicle } from '../vehicle';
// import { Observable ,  BehaviorSubject } from 'rxjs';
// import { AppConfig } from '../../config';


@Injectable({
  providedIn: 'root'
})

export class ReservationService {
  constructor(private http: HttpClient, private config: AppConfig) { }

  getReservation(reservationId: string): Observable<Reservation> {
    return this.http.get<Reservation>( this.config.serveUrl + 'reservation/reservation/' + reservationId + "/");
  }


}

