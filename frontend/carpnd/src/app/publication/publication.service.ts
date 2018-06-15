import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../config';
import { Observable } from 'rxjs';
import { Publication } from './publication';
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

 /* getUserList(): Observable<[User]> {
  	return this.http.get<[User]>( this.config.serveUrl + 'publication/user/list');
  }

  createUser(user:User): Observable<any> {
  	return this.http.post<any>( this.config.serveUrl + 'publication/user/new', user);
  }

  deleteUser(user: User): Observable<any> {
  	return this.http.post<any>(this.config.serveUrl + 'publication/user/delete', user);
  }

  updateUser(user: User): Observable<any> {
    return this.http.post<any>( this.config.serveUrl + 'publication/user/edit', user);
  }

  loadUserToEdit(user: User) {
    this.userSource.next(user); 
  }*/

}
