import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SinginComponent } from './Usuarios/singin/singin.component';
import { HomeComponent } from './Usuarios/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginEmpresaComponent } from './Empresas/login-empresa/login-empresa.component';
import { LoginCandidatoComponent } from './Candidatos/login-candidato/login-candidato.component';
import { MenuComponent } from './Menu/menu.component';
import { RegistroLaboralComponent } from './Candidatos/registro-laboral/registro-laboral.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { VistaEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/vista-equipo-trabajo/vista-equipo-trabajo.component';
import { CrearEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/crear-equipo-trabajo/crear-equipo-trabajo.component';

@NgModule({
  declarations: [
    AppComponent,
    SinginComponent,
    HomeComponent,
    LoginEmpresaComponent,
    LoginCandidatoComponent,
    MenuComponent,
    RegistroLaboralComponent,
    VistaEquipoTrabajoComponent,
    CrearEquipoTrabajoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
