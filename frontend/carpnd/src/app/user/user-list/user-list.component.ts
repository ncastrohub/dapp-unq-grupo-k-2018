import { Component, OnInit } from '@angular/core';
import { User } from '../user'
import { Router } from '@angular/router';
import { UserServiceService } from '../service/user-service.service';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})


export class UserListComponent implements OnInit {

  errorList = [];
  userList: [User];

   constructor(public service: UserServiceService, private router:Router, public authService: AuthService) { }

  ngOnInit() {
  	this.getUserList();
  }

  getUserList(){
    this.service.getUserList().subscribe(
      data => this.userList = data,
      error => this.errorList.push(error)
    );
  }

  editLoggedUser(){
    this.router.navigate(['/user/create']);
  }

  edit(user:User){
    this.service.loadUserToEdit(user);
    this.router.navigate(['/user/edit']);
  }

  delete(userId:number){
    this.service.deleteUser(userId).subscribe(
      data => this.getUserList(),
      error => this.errorList.push(error)
    )
  }

}
