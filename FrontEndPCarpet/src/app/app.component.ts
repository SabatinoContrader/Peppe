import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/User';
import { NgForm } from '../../node_modules/@angular/forms';
import { Router } from '../../node_modules/@angular/router';

import {} from '@angular/platform-browser-dynamic';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app';
  

  ngOnInit(){

  }

  
  
}
