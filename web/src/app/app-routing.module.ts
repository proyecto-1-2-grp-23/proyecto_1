import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SinginComponent } from './Usuarios/singin/singin.component';
import { HomeComponent } from './Usuarios/home/home.component';
import { MenuComponent } from './Menu/menu.component';
import { RegistroLaboralComponent } from './Candidatos/registro-laboral/registro-laboral.component';
import { VistaEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/vista-equipo-trabajo/vista-equipo-trabajo.component';
import { CrearEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/crear-equipo-trabajo/crear-equipo-trabajo.component';
import { LoginEmpresaComponent } from './Empresas/login-empresa/login-empresa.component';
import { LoginCandidatoComponent } from './Candidatos/login-candidato/login-candidato.component';
import { AgregarCandidatoEquipoComponent } from './Empresas/EquipoDeTrabajo/crear-equipo-trabajo/agregar-candidato-equipo/agregar-candidato-equipo.component';
import { EntrevistasProgramadasComponent } from './Entrevistas/entrevistas-programadas/entrevistas-programadas.component';
import { ProgramarEntrevistaComponent } from './Entrevistas/programar-entrevista/programar-entrevista.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
    pathMatch: 'full',
  },
  {
    path: 'signin/:tipoUsuario',
    component: SinginComponent,
    pathMatch: 'full',
  },
  {
    path: 'loginEmpresa',
    component: LoginEmpresaComponent,
    pathMatch: 'full',
  },
  {
    path: 'loginCandidato',
    component: LoginCandidatoComponent,
    pathMatch: 'full',
  },
  {
    path: 'menu/:tipoUsuario',
    component: MenuComponent,
    pathMatch: 'full',
  },
  {
    path: 'registroLaboral',
    component: RegistroLaboralComponent,
    pathMatch: 'full',
  },
  {
    path: 'equipoDeTrabajo',
    component: VistaEquipoTrabajoComponent,
    pathMatch: 'full',
  },
  {
    path: 'crearEquipoDeTrabajo',
    component: CrearEquipoTrabajoComponent,
    pathMatch: 'full',
  },
  {
    path: 'crearEquipoDeTrabajo/agregarCandidato',
    component: AgregarCandidatoEquipoComponent,
    pathMatch: 'full',
  },
  {
    path: 'entrevistas',
    component: EntrevistasProgramadasComponent,
    pathMatch: 'full',
  },
  {
    path: 'entrevistas/programarEntrevista',
    component: ProgramarEntrevistaComponent,
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
