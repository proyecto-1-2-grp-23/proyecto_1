import unittest

from ..src.models.project import Project



from ..src.main import app

from ..src.session import Session, engine
from ..src.models.model import Base
from ..src.session import Session, engine
import json

data = {
    "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
    "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
    "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
    "conocimientos_tecnicos":"TML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
    "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."

}

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
            "conocimientos_tecnicos":"TML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
            "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."
            }
        
    def test_obtener_proyecto(self):
        with app.app_context():
            proyecto_existente = self.session.query(Project).filter_by(nombre=data['nombre']).first()
            if proyecto_existente:
                response = self.app.get(f'/projects/listar-projects')
                project_data = response.get_json()
                assert 'id' in project_data
                assert response.status_code == 200  # Debe ser 200 si la solicitud GET es exitosa
          



  

    def test_creacion_proyecto(self):
        
        with app.app_context():
        
            if data:
                # Si el proyecto existe, no debe poder crearse nuevamente
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                if(response.status_code == 201):
                    self.assertEqual(response.status_code, 201)  # Código 409 para indicar un conflicto
                else:
                    self.assertEqual(response.status_code, 412)
    
    def test_validacion_(self):
        data = {
            "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
            "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
            "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
            "conocimientos_tecnicos":"TML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
            "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."
            }
        # Datos de ejemplo para la prueba
        with app.app_context():
            proyecto_existente = self.session.query(Project).filter_by(nombre=data['nombre']).first()
            if proyecto_existente:
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
                #self.assertEqual(response_data['error'], 'El usuario ya existe')
            else:
                # Si el usuario no existe, se puede crear
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

    def test_validacion_perfiles(self):
        data = {
            "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
            "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
            "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
            "conocimientos_tecnicos":"TML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
            "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."
            }
        # Verificar si el usuario ya existe en la base de datos
        with app.app_context():
            proyecto_existente = self.session.query(Project).filter_by(nombre=data['nombre']).first()
            if proyecto_existente:
                # Si el usuario existe, no se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
               
            else:
                # Si el usuario no existe, se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(perfiles=data['perfiles']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(nuevo_proyecto.perfiles, data['perfiles'])
    
    def test_validacion_conocimientos_tecnicos(self):
        data = {
            "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
            "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
            "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
            "conocimientos_tecnicos":"HTML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
            "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."
            }
        # Verificar si el usuario ya existe en la base de datos
        with app.app_context():
            proyecto_existente = self.session.query(Project).filter_by(nombre=data['nombre']).first()
            if proyecto_existente:
                # Si el proyecto existe, no se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
               
            else:
                # Si el proyecto no existe, se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(conocimientos_tecnicos=data['conocimientos_tecnicos']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(nuevo_proyecto.conocimientos_tecnicos, data['conocimientos_tecnicos'])
    
    def test_validacion_habilidades_blandas(self):
        data = {
            "nombre": "Desarrollo de una Aplicación de Comercio Electrónico",
            "descripcion": "Crear una aplicación de comercio electrónico para una tienda de ropa en línea.",
            "perfiles": "Desarrollador web, diseñador de UX/UI, gerente de proyecto.",
            "conocimientos_tecnicos":"HTML, CSS, JavaScript, React, Node.js, Express, MongoDB, diseño de bases de datos, Git.",
            "habilidades_blandas":"Trabajo en equipo, comunicación efectiva, gestión del tiempo, resolución de problemas."
            }
        # Verificar si el usuario ya existe en la base de datos
        with app.app_context():
            proyecto_existente = self.session.query(Project).filter_by(nombre=data['nombre']).first()
            if proyecto_existente:
                # Si el proyecto existe, no se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
               
            else:
                # Si el proyecto no existe, se crea
                response = self.app.post('/projects', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                nuevo_proyecto = self.session.query(Project).filter_by(habilidades_blandas=data['habilidades_blandas']).first()
                self.assertIsNotNone(nuevo_proyecto)
                self.assertEqual(nuevo_proyecto.habilidades_blandas, data['habilidades_blandas'])

        
    
    
    
    def teardown_method(self, args):
        self.session.close()
        Base.metadata.drop_all(bind=engine)


    