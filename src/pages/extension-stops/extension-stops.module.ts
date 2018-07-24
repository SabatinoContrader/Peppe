import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ExtensionStopsPage } from './extension-stops';
import { ExtensionStopsRowComponent } from '../../components/extension-stops-row/extension-stops-row';

@NgModule({
  declarations: [
    ExtensionStopsPage,
    ExtensionStopsRowComponent
  ],
  imports: [
    IonicPageModule.forChild(ExtensionStopsPage),
  ],
})
export class ExtensionStopsPageModule {}
