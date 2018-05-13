import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Vehicle } from '../vehicle';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class VehicleService {

  constructor(private http: HttpClient) { }

  getVehicleList(userId: string): Observable<[Vehicle]> {
  	return this.http.get<[Vehicle]>('http://localhost:8080/api/publication/'+ userId +'/vehicle/list');
  }

}
