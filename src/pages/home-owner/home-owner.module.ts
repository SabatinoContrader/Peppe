import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomeOwnerPage } from './home-owner';
import { AgmCoreModule } from '../../../node_modules/@agm/core';

@NgModule({
  declarations: [
    HomeOwnerPage,
  ],
  imports: [
    IonicPageModule.forChild(HomeOwnerPage),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAUf_fIZF0iu40Uiwhj3RhFE3Kd1KrWUFw',
      libraries: ["places"]
    })
  ],
})
export class HomeOwnerPageModule {}
