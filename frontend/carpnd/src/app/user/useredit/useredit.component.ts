import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserServiceService } from '../service/user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-useredit',
  templateUrl: './useredit.component.html',
  // styleUrls: ['../createuser/createuser.component.css']
})

export class UsereditComponent implements OnInit {

  constructor(private service: UserServiceService, private router: Router) { }
  
  user:User;

  ngOnInit() {
    this.service.getUser().subscribe(
      user => {
        this.user = user
      }
    );
  }

  errorList = [];

  onSubmit() {
    this.service.updateUser(this.user).subscribe(
        data => {
          this.router.navigate(['/']);
        },
        error => this.errorList.push(error)
      );
    }

}
