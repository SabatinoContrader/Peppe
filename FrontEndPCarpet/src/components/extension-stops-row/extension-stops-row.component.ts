import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Stop } from '../../models/Stop';
import { StopService } from '../../services/stop.service';
import { Router } from '@angular/router';


@Component({
  selector: 'tr[app-extension-stops-row]',
  templateUrl: './extension-stops-row.component.html',
  styleUrls: ['./extension-stops-row.component.css']
})
export class ExtensionStopsRowComponent implements OnInit {

  @Input() stop: Stop;
  price: number;
  
  constructor(private stopService: StopService, private router: Router) { }

  ngOnInit() {
  }

  detectChange(f:NgForm): void{
    console.log("time: "+ f.value.select);
    this.price = (this.stop.price / 60) * f.value.select; 
}

prolungaSosta(f: NgForm): void{
  this.stopService.prolungaSosta(f.value.select, this.stop).subscribe((response) => {
    this.stop.finish = response;
  });
}

}
