import { Component, OnInit } from '@angular/core';
import { PublicationService } from '../publication.service';
import { Publication, ReserveParameters,Location } from '../publication';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
	publication: Publication;
  parameters: ReserveParameters;
  errorList = [];


  reservationDays;
  costumerId: number;

	constructor(private service: PublicationService, private router: Router, private route: ActivatedRoute) { }

	ngOnInit() {
    this.parameters = new ReserveParameters(); 
    let publicationId = this.route.snapshot.paramMap.get('publicationId');
		this.route.queryParams.subscribe(params => {
			this.service.getPublication(publicationId).subscribe(data => {
				this.publication = data;
			})   
	  })
  }

  addReservationDays($event){
    this.parameters.reservationDays = $event.dateList;
  }

  setReturnLocation(location:Location) {
    this.parameters.returnLocation = location.id;
  }

  makeReservation(){
    this.parameters.customer = this.costumerId;
    this.parameters.publication = this.publication.id;
    this.service.makeReservation(this.parameters).subscribe(data => {
        this.router.navigate(['/reservation/detail/' + data.id]);
      },
      error => this.errorList.push(error)
    );
  }

}
