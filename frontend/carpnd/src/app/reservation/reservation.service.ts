import { Injectable } from '@angular/core';
import { Observable ,  BehaviorSubject } from 'rxjs';
import { AppConfig } from '../config';
import { Reservation } from './reservation';
import { HttpClient } from '@angular/common/http';
import { Page } from '../publication/publication';

@Injectable({
  providedIn: 'root'
})

export class ReservationService {
  constructor(private http: HttpClient, private config: AppConfig) { }

  getReservation(reservationId: string): Observable<Reservation> {
    return this.http.get<Reservation>( this.config.serveUrl + 'reservation/' + reservationId + "/");
  }

  getReservationList(): Observable<Page<Reservation>> {
  	return this.http.get<Page<Reservation>>( this.config.serveUrl + 'reservation/list');
  }

  getReservationForUrl(url): Observable<Page<Reservation>> {
    return this.http.get<Page<Reservation>>( this.config.serveUrl + url);
  }

  getReservationListCustomer(): Observable<Page<Reservation>> {
  	return this.http.get<Page<Reservation>>( this.config.serveUrl + 'reservation/list/customer');
  }

  getReservationListOwner(): Observable<Page<Reservation>> {
  	return this.http.get<Page<Reservation>>( this.config.serveUrl + 'reservation/list/owner');
  }

}

