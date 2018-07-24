import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomeDriverPage } from './home-driver';
import { AgmCoreModule } from '../../../node_modules/@agm/core';

@NgModule({
  declarations: [
    HomeDriverPage,
  ],
  imports: [
    IonicPageModule.forChild(HomeDriverPage),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAUf_fIZF0iu40Uiwhj3RhFE3Kd1KrWUFw',
      libraries: ["places"]
    })
  ],
})
export class HomeDriverPageModule {}
