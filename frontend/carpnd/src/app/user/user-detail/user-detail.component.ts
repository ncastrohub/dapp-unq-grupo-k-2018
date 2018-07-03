import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})

export class UserDetailComponent implements OnInit {

  constructor(private service: UserServiceService, private router: Router) { }

  user:User;
  loading: boolean;
  ngOnInit() {
    this.loading = true;
  	this.service.getUser().subscribe(
      user => {
        this.user = user;
        this.loading = false;
      }
    );
  }

  editUser(){
  	this.router.navigate(['/user/edit'])
  }

}
