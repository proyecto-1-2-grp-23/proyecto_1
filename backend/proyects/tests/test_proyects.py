import unittest

from ..src.models.project import Project


from ..src.main import app

from ..src.session import Session, engine
from ..src.models.model import Base
from ..src.session import Session, engine
import json


class TestMiApp(unittest.TestCase):

    def setUp(self):
        app.config['TESTING'] = True
        self.app = app.test_client()
        Base.metadata.create_all(engine)
        self.session = Session()
        self.data = {
            "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
            "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
            "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
            "conocimientos_tecnicos": "Python, SQL, Docker ,Angular",
            "habilidades_blandas": "Trabajo en equipo, comunicacion efectiva , gestion del tiempo, resolucion de problemas"
        }
        self.update_data ={
            "id": 1,
            "nombre": "Desesarrollo de una Aplicación Web usando Angular",
            "descripcion": "Crear una aplicación Web para una tienda de ropa.",
            "perfiles": "Desarrollador web, UX/UI, desarrollador backend",
            "conocimientos_tecnicos": "Python, SQL, Docker, Angular, Postgres",
            "habilidades_blandas": "Trabajo en equipo, Resolucion de problemas, Agilidad en resolucion de problemas"
        }

        self.invalid_update_data = {
            "nombre": "Nombre actualizado sin ID",
            "descripcion": "Crear una aplicación Web para una tienda de ropa.",
        }

    def test_obtener_proyecto(self):
        with app.app_context():
            proyecto_existente = self.session.query(
                Project).filter_by(nombre=self.data['nombre']).first()
            if proyecto_existente:
                response = self.app.get(f'/projects/listar-projects')
                project_data = response.get_json()
                assert 'id' in project_data
                assert response.status_code == 200  # Debe ser 200 si la solicitud GET es exitosa

    def test_creacion_proyecto(self):

        with app.app_context():

            if self.data:
                # Si el proyecto existe, no debe poder crearse nuevamente
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                if (response.status_code == 201):
                    # Código 409 para indicar un conflicto
                    self.assertEqual(response.status_code, 201)
                else:
                    self.assertEqual(response.status_code, 412)

    
    def test_validacion_(self):

        with app.app_context():
            proyecto_existente = self.session.query(
                Project).filter_by(nombre=self.data['nombre']).first()
            if proyecto_existente:
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                # Código 409 para indicar un conflicto
                self.assertEqual(response.status_code, 412)
                # self.assertEqual(response_data['error'], 'El usuario ya existe')
            else:
                # Si el usuario no existe, se puede crear
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

    
    
    
    def test_validacion_perfiles(self):
        with app.app_context():
            proyecto_existente = self.session.query(
                Project).filter_by(nombre=self.data['nombre']).first()
            if proyecto_existente:
                # Si el usuario existe, no se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                # Código 409 para indicar un conflicto
                self.assertEqual(response.status_code, 412)

            else:
                # Si el usuario no existe, se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(
                    perfiles=self.data['perfiles']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(nuevo_proyecto.perfiles,
                                 self.data['perfiles'])

    def test_validacion_conocimientos_tecnicos(self):
        with app.app_context():
            proyecto_existente = self.session.query(
                Project).filter_by(nombre=self.data['nombre']).first()
            if proyecto_existente:
                # Si el proyecto existe, no se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                # Código 409 para indicar un conflicto
                self.assertEqual(response.status_code, 412)

            else:
                # Si el proyecto no existe, se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(
                    conocimientos_tecnicos=self.data['conocimientos_tecnicos']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(
                    nuevo_proyecto.conocimientos_tecnicos, self.data['conocimientos_tecnicos'])

    def test_validacion_habilidades_blandas(self):

        with app.app_context():
            proyecto_existente = self.session.query(
                Project).filter_by(nombre=self.data['nombre']).first()
            if proyecto_existente:
                # Si el proyecto existe, no se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                # Código 409 para indicar un conflicto
                self.assertEqual(response.status_code, 412)

            else:
                # Si el proyecto no existe, se crea
                response = self.app.post(
                    '/projects', data=json.dumps(self.data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(habilidades_blandas=self.data['habilidades_blandas']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(
                    nuevo_proyecto.habilidades_blandas, self.data['habilidades_blandas'])
                
    def test_update_proyect(self):
        response_create = self.app.post('/projects', data=json.dumps(self.data), content_type='application/json')
        self.assertEqual(response_create.status_code, 201)  # Verifica si la creación del proyecto es exitosa

        response_update = self.app.put('/projects/1', data=json.dumps(self.update_data), content_type='application/json')
        self.assertEqual(response_update.status_code, 200)  # Verifica si la actualización es exitosa
    
    def test_update_project_missing_fields(self):
            with app.app_context():
                if self.data:
                    # Crear un proyecto con datos completos
                    response_create = self.app.post('/projects', data=json.dumps(self.data), content_type='application/json')
                    self.assertEqual(response_create.status_code, 201)  # Verifica si la creación del proyecto es exitosa

                    # Crear datos para la actualización con campos faltantes
                    invalid_update_data = {
                        "nombre": "Nombre actualizado sin ID",  # Campo 'id' faltante
                        # Omitir otros campos requeridos
                    }

                    # Intentar actualizar el proyecto con datos faltantes
                    response_update = self.app.put('/projects/1', data=json.dumps(invalid_update_data), content_type='application/json')
                    self.assertEqual(response_update.status_code, 200)  # Verifica si se manejan los campos faltantes con un código de error 200
                   

    def teardown_method(self, args):
        self.session.close()
        Base.metadata.drop_all(bind=engine)
