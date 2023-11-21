import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-presentar-prueba',
  templateUrl: './presentar-prueba.component.html',
  styleUrls: ['./presentar-prueba.component.css'],
})
export class PresentarPruebaComponent implements OnInit {
  registrationForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  pregunta: any = 'Pregunta 1';
  respuestas: any = [
    { Nombre: 'Respuesta 1', Correcta: '0' },
    { Nombre: 'Respuesta 2', Correcta: '0' },
    { Nombre: 'Respuesta 3', Correcta: '0' },
  ];

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      selectedString: new FormControl(''),
      pregunta: new FormControl(''),
    });
  }

  get selectedString() {
    return this.registrationForm.get('selectedString');
  }

  toggleSelection(selectedItem: string) {
    console.log(this.selectedString?.value, 'this.selectedString?.value');

    if (this.selectedString?.value === selectedItem) {
      // Desseleccionar la opción actual si ya está seleccionada
      this.selectedString.setValue('');
    } else {
      // Seleccionar la nueva opción y deseleccionar las demás
      this.selectedString?.setValue(selectedItem);
    }
  }

  siguiente() {}
}
