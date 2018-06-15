import { Component, OnInit } from '@angular/core';
import { PublicationService } from '../publication.service';
import { Publication } from '../publication';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
	publication: Publication;

  	constructor(private service: PublicationService, private router: Router, private route: ActivatedRoute) { }

  	ngOnInit() {
      let publicationId = this.route.snapshot.paramMap.get('publicationId');
  		this.route.queryParams.subscribe(params => {
  			this.service.getPublication(publicationId).subscribe(data => {
  				this.publication = data;
  			})   
		})
	}
}
