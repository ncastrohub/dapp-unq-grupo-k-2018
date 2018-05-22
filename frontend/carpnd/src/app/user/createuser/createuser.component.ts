import { Component, OnInit } from '@angular/core';
import { User } from '../user'



@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.css']
})
export class CreateuserComponent implements OnInit {

  user:User;

  	constructor() { 
  		this.user = new User();
	}

  ngOnInit() {
  }

}
