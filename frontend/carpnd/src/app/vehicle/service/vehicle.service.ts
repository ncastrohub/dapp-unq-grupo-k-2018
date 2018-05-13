import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vehicle } from '../vehicle';
import { Observable } from 'rxjs/Observable';
import { AppConfig } from '../../config';


@Injectable()
export class VehicleService {

  constructor(private http: HttpClient, private config: AppConfig) { }

  getVehicleList(userId: string): Observable<[Vehicle]> {
  	return this.http.get<[Vehicle]>( this.config.serveUrl + '/publication/'+ userId +'/vehicle/list');
  }

  createVehicle(userId: string, vehicle: Vehicle): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + '/publication/'+ userId +'/vehicle/new', vehicle);
  }

  

}
