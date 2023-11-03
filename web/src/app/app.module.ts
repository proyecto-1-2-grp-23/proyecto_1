import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SinginComponent } from './Usuarios/singin/singin.component';
import { HomeComponent } from './Usuarios/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MenuComponent } from './Menu/menu.component';
import { RegistroLaboralComponent } from './Candidatos/registro-laboral/registro-laboral.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginEmpresaComponent } from './Empresas/login-empresa/login-empresa.component';
import { LoginCandidatoComponent } from './Candidatos/login-candidato/login-candidato.component';
import { VistaEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/vista-equipo-trabajo/vista-equipo-trabajo.component';
import { CrearEquipoTrabajoComponent } from './Empresas/EquipoDeTrabajo/crear-equipo-trabajo/crear-equipo-trabajo.component';
import { AgregarCandidatoEquipoComponent } from './Empresas/EquipoDeTrabajo/crear-equipo-trabajo/agregar-candidato-equipo/agregar-candidato-equipo.component';
import { MatDialogModule } from '@angular/material/dialog';
import { EntrevistasProgramadasComponent } from './Entrevistas/entrevistas-programadas/entrevistas-programadas.component';
import { ProgramarEntrevistaComponent } from './Entrevistas/programar-entrevista/programar-entrevista.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { DetalleEntrevistaComponent } from './Entrevistas/detalle-entrevista/detalle-entrevista.component';
import { ResultadoEntrevistaComponent } from './Entrevistas/resultado-entrevista/resultado-entrevista.component';

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
    AgregarCandidatoEquipoComponent,
    EntrevistasProgramadasComponent,
    ProgramarEntrevistaComponent,
    DetalleEntrevistaComponent,
    ResultadoEntrevistaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    HttpClientModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
