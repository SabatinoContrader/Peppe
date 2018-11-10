import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LayoutModule } from '../../../layouts/layout.module';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { NgForm, FormsModule } from '@angular/forms';
import { ParksMapComponent } from './parksMap.component';
import { AgmCoreModule } from '../../../../../../node_modules/@agm/core';

const routes: Routes = [
  {
      "path": "",
      "component": AsideLeftDisplayDisabledComponent,
      "children": [
          {
              "path": "",
              "component": ParksMapComponent
          }
      ]
  }
];

@NgModule({
    imports: [
        CommonModule, RouterModule.forChild(routes), LayoutModule,
        AgmCoreModule
    ],
exports: [
    RouterModule
],
  declarations: [ParksMapComponent]
})
export class ParksMapModule { }
