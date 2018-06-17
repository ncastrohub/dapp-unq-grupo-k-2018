import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config';
import { Observable } from 'rxjs';
import { Publication, ReserveParameters } from './publication';
import { Page, CreatePublication } from './publication';


@Injectable()
export class PublicationService {

  constructor(private http: HttpClient, private config: AppConfig) {}

  getPublicationList(): Observable<Page<Publication>> {
  	return this.http.get<Page<Publication>>( this.config.serveUrl + 'publication/publication/list/');
  }

  createPublication(userId: string, publication: CreatePublication): Observable<CreatePublication> {
  	return this.http.post<CreatePublication>( this.config.serveUrl + 'publication/' + userId +  '/publication/create/' , publication);
  }

  getPublication(publicationId: string): Observable<Publication> {
    return this.http.get<Publication>( this.config.serveUrl + 'publication/publication/' + publicationId + "/");
  }

  makeReservation(parameters: ReserveParameters): Observable<any> {
    return this.http.post<any>( this.config.serveUrl + 'reservation/reserve/', parameters);
  }

}
