import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ServicioProyectosService } from '../Servicio/servicio-proyectos.service';


@Component({
  selector: 'app-agregar-candidato-proyecto',
  templateUrl: './agregar-candidato-proyecto.component.html',
  styleUrls: ['./agregar-candidato-proyecto.component.css'],
})
export class AgregarCandidatoProyectoComponent implements OnInit {
  formulario: FormGroup;
  id!: any
  tecnicas: any;
  registrationForm: FormGroup = new FormGroup({
    candidatosCompatibles: new FormControl('', [Validators.required]),
    caracteristicasPersonalidad: new FormControl('', [Validators.required]),
    caracteristicasTecnicas: new FormControl('', [Validators.required]),
  });
  personalidades: any;
  selectedValuePersonalidad!: string;
  selectedValueTEcnicas: any;
  candidatos: string[] = [];
  numero_id_candidato!: number;
  numero_id!: number;



  constructor(
    public dialogRef: MatDialogRef<AgregarCandidatoProyectoComponent>,
    private proyectoService: ServicioProyectosService,
    private formBuilder: FormBuilder) {
      this.formulario = this.formBuilder.group({
        candidatosCompatibles: new FormControl('')
      });
    }

  ngOnInit(): void {
    this.proyectoService.listarProyectos().subscribe((res) => {
      const agregarCandidatosFunction = localStorage.getItem('agregarCandidatosFunction');
      const value: string | null = agregarCandidatosFunction;
      const stringValue: string = value as string;
      this.numero_id = parseInt(stringValue, 10)
      console.log(this.numero_id)
      //if(this.numero_id == 3){
      //  this.numero_id = this.numero_id -1
      //}{}
      //  this.numero_id = res[this.numero_id].id;

      this.obtenerid()
    });

  }

  agregar() {

    console.log("Numero Candidato",this.numero_id_candidato);
    console.log("Numero Proyecto",this.numero_id);

    this.dialogRef.close();
  }

  verProyectos() {
    this.dialogRef.close();
  }

  obtenerid() {
    this.proyectoService.listarProyectosById(this.numero_id).subscribe((res) => {
      this.tecnicas = res[0];
      this.personalidades = res[1];

      console.log(this.tecnicas)
      console.log(this.personalidades)
      console.log(typeof (this.tecnicas));

    })
  }

  onCaracteristicasTecnicasChange(){
    const caracteristicasTecnicasControl = this.registrationForm.get('caracteristicasTecnicas');
    if (caracteristicasTecnicasControl) {
      this.selectedValueTEcnicas = caracteristicasTecnicasControl.value;
      console.log(this.selectedValueTEcnicas)
      console.log(typeof(this.selectedValueTEcnicas))

    }
  }
  onCaracteristicasPersonalidadChange(){
    const caracteristicasPersonalidad = this.registrationForm.get('caracteristicasPersonalidad');
    if (caracteristicasPersonalidad) {
      this.selectedValuePersonalidad = caracteristicasPersonalidad.value;
      console.log(this.selectedValuePersonalidad)
      console.log(typeof(this.selectedValuePersonalidad))

      if(this.selectedValuePersonalidad && this.selectedValueTEcnicas)
      {
        this.queryParamsCandidatoChange()
      }




    }

  }

  queryParamsCandidatoChange(){
    const parametroConEspacios = this.selectedValuePersonalidad;
      const selectedValueTEcnicas = this.selectedValueTEcnicas
      this.proyectoService.getDataWithQueryParams(parametroConEspacios, selectedValueTEcnicas)
      .subscribe(response => {
        // Handle the response data here.

        for (let i = 0; i < response.length; i++) {
          this.candidatos.push(response[i].nombreCompleto);
          if (i === 0) {
            this.numero_id_candidato = response[i].id;
            console.log("seleccionar",this.numero_id_candidato) // Seleccionar el ID del primer elemento
          }
        }
        this.numero_id_candidato = response[0].id;
        console.log(typeof(response[0].id))
        this.otraFuncionQueUsaId()

      });
  }
  otraFuncionQueUsaId() {
    console.log(this.numero_id_candidato); // Verificar this.numero_id aquí
    // ... Hacer algo con this.numero_id
  }

  obtenerValor(event: Event) {
    const selectElement    = (event.target as HTMLSelectElement);
    const valorSeleccionado = selectElement.value;
    const idSeleccionado = selectElement.selectedOptions[0].getAttribute('id');

    console.log('ID seleccionado:', idSeleccionado);
  }

}
