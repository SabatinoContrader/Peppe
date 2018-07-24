import { Component, Input } from '@angular/core';
import { Stop } from '../../models/Stop';
import { NgForm } from '../../../node_modules/@angular/forms';
import { StopProvider } from '../../providers/stop/stop';

/**
 * Generated class for the ExtensionStopsRowComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */

@Component({
  selector: 'tr[extension-stops-row]',
  templateUrl: 'extension-stops-row.html'
})
export class ExtensionStopsRowComponent {
 
  @Input()
  public stop: Stop;
  price: number;

  constructor(private stopProvider: StopProvider) {
    console.log('Hello ExtensionStopsRowComponent Component');
  }

  ngOnChanges() {
    console.log("mystopp: " + stop);
  }

  detectChange(f:NgForm): void{
    console.log("time: "+ f.value.select);
    this.price = (this.stop.price / 60) * f.value.select; 
}

prolungaSosta(f: NgForm): void{
  this.stopProvider.prolungaSosta(f.value.select, this.stop).subscribe((response) => {
    this.stop.finish = response;
  });
}

}
