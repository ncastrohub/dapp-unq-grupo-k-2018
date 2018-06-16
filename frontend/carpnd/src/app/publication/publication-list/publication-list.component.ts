import { Component, OnInit } from '@angular/core';
import { Publication, Page } from '../publication';
import { PublicationService } from '../publication.service';
import { Router } from '@angular/router';

// AGREGADO PARA AUTENTICACION
import { AuthService } from '../../auth/auth.service';
// FIN AGREGADO

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css']
})

export class PublicationListComponent implements OnInit {

  errorList = [];
  publicationList: Page<Publication>;

  constructor(private service: PublicationService, private router: Router, public authService: AuthService) { }

  ngOnInit() {
  	this.getPublicationList();
  }


  getPublicationList(){
    this.service.getPublicationList().subscribe(
      data => this.publicationList = data,
      error => this.errorList.push(error)
    );
  }

  goToDetails(publicationId:string) {
    this.router.navigate(['/publication/detail/' + publicationId]);
  }

}
