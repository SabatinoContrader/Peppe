import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { StopService } from "../../services/stop.service";
import { Stop } from "../../models/Stop";
import { FormsModule } from "@angular/forms";


@Component({
  selector: "app-extension-stops",
  templateUrl: "./extension-stops.component.html",
  styleUrls: ["./extension-stops.component.css"]
})

export class ExtensionStopsComponent implements OnInit {

 
  myStopsList: Stop[];
  constructor(private stopService: StopService, private router: Router) { 

  }

  ngOnInit() {
     this.stopService.myStopsList().subscribe(response => {this.myStopsList = response});
  }

  

  //prolungaSosta(f)
    

  //script per visualizzare il prezzo andra' qua? + prezzo iniziale in onInit?
  
}
