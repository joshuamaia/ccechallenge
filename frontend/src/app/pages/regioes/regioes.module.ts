import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegioesRoutingModule } from './regioes-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RegioesRoutingModule,
    SharedModule
  ]
})
export class RegioesModule { }
