import { FormsModule } from '@angular/forms';
import { StopsInProgressRowComponent } from './stops-in-progress-row.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AsideLeftDisplayDisabledComponent } from '../aside-left-display-disabled.component';
import { LayoutModule } from '../../../layouts/layout.module';

const routes: Routes = [
  {
      "path": "",
      "component": AsideLeftDisplayDisabledComponent,
      "children": [
          {
              "path": "",
              "component": StopsInProgressRowComponent
          }
      ]
  }
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(routes), LayoutModule, FormsModule
],
exports: [
    RouterModule
],
  declarations: [StopsInProgressRowComponent]
})
export class StopsInProgressRowModule { }
