import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { SlotDetailsPage } from './slot-details';

@NgModule({
  declarations: [
    SlotDetailsPage,
  ],
  imports: [
    IonicPageModule.forChild(SlotDetailsPage),
  ],
})
export class SlotDetailsPageModule {}
