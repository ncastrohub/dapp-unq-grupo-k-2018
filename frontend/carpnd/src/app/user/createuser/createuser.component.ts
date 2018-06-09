import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.css']
})
export class CreateuserComponent {

 constructor(private service: UserServiceService, private router: Router, public authService: AuthService) {
    this.user = new User();
  }

  user:User;

  submitted = false;
  errorList = [];

  onSubmit() {
	  this.service.createUser(this.user).subscribe(
	    data => {
	      this.router.navigate(['/user/list']);
	    },
	    error => this.errorList.push(error)
	  );
  }

}
