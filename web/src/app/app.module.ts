import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule } from '@angular/common/http';
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
import { AgregarCandidatoEquipoComponent } from './Empresas/EquipoDeTrabajo/agregar-candidato-equipo/agregar-candidato-equipo.component';
import { MatDialogModule } from '@angular/material/dialog';
import { EntrevistasProgramadasComponent } from './Entrevistas/entrevistas-programadas/entrevistas-programadas.component';
import { ProgramarEntrevistaComponent } from './Entrevistas/programar-entrevista/programar-entrevista.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { DetalleEntrevistaComponent } from './Entrevistas/detalle-entrevista/detalle-entrevista.component';
import { ResultadoEntrevistaComponent } from './Entrevistas/resultado-entrevista/resultado-entrevista.component';
import { ListaProyectosComponent } from './Proyectos/lista-proyectos/lista-proyectos.component';
import { CrearProyectoComponent } from './Proyectos/crear-proyecto/crear-proyecto.component';
import { AgregarCandidatoProyectoComponent } from './Proyectos/agregar-candidato-proyecto/agregar-candidato-proyecto.component';
import { ListaCandidatosEquipoComponent } from './Empresas/EquipoDeTrabajo/lista-candidatos-equipo/lista-candidatos-equipo.component';
import { DetalleProyectoComponent } from './Proyectos/detalle-proyecto/detalle-proyecto.component';
import { ListarCandidatosProyectoComponent } from './Proyectos/listar-candidatos-proyecto/listar-candidatos-proyecto.component';
import { ModificarProyectoComponent } from './Proyectos/modificar-proyecto/modificar-proyecto.component';
import { CrearPreguntasComponent } from './PruebaTecnica/crear-preguntas/crear-preguntas.component';
import { PresentarPruebaComponent } from './PruebaTecnica/presentar-prueba/presentar-prueba.component';
import { RegistrarResultadoComponent } from './PruebaTecnica/registrar-resultado/registrar-resultado.component';
import { VerResultadoComponent } from './PruebaTecnica/ver-resultado/ver-resultado.component';
import { CrearPruebaDesempeñoComponent } from './Prueba Desempeño/crearPruebaDesempeño/crearPruebaDesempeño.component';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

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
    ListaProyectosComponent,
    CrearProyectoComponent,
    AgregarCandidatoProyectoComponent,
    ListaCandidatosEquipoComponent,
    DetalleProyectoComponent,
    ListarCandidatosProyectoComponent,
    ModificarProyectoComponent,
    CrearPreguntasComponent,
    PresentarPruebaComponent,
    RegistrarResultadoComponent,
    VerResultadoComponent,
    CrearPruebaDesempeñoComponent,
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
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    TranslateModule.forRoot({
      defaultLanguage: 'en',
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
