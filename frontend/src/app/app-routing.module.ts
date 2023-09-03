import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./pages/agentes/agentes.module').then((m) => m.AgentesModule)
  },
  {
    path: 'regioes',
    loadChildren: () => import('./pages/regioes/regioes.module').then((m) => m.RegioesModule)
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
