import unittest
from ..src.main import app
from ..src.models.candidato import Candidato, CandidatoSchema
from ..src.models.user import  User, Base
from ..src.session import Session, engine
from ..src.models.model import Base
from ..src.session import Session, engine
import json

data = {
            'correo': 'usuario324@ejemplo.com',
            'password': '34234',
            'ciudad': 'Bogota',
            'pais': 'Colombia',
            'telefono': '43534543',
            'nombreCompleto': 'Carlos Arango',
            'edad': 34,
            'idiomas': 'Español',
            'rasgosPersonalidad': 'Sociable'
        }

class TestMiApp(unittest.TestCase):
        
    def setUp(self):
        app.config['TESTING'] = True
        self.app = app.test_client()
        Base.metadata.create_all(engine)
        self.session = Session()

    def test_creacion_usuario(self):
        
        with app.app_context():
        
            if data:
                # Si el usuario existe, no debe poder crearse nuevamente
                response = self.app.post('/users', data=json.dumps(data), content_type='application/json')
                if(response.status_code == 201):
                    self.assertEqual(response.status_code, 201)  # Código 409 para indicar un conflicto
                else:
                    self.assertEqual(response.status_code, 412)
    
    def test_validacion_usuario_y_candidato(self):
        # Datos de ejemplo para la prueba

       with app.app_context():
            usuario_existente = self.session.query(User).filter_by(correo=data['correo']).first()
            if usuario_existente:
                response = self.app.post('/users', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
                response_data = json.loads(response.data)
                #self.assertEqual(response_data['error'], 'El usuario ya existe')
            else:
                # Si el usuario no existe, se puede crear
                response = self.app.post('/users', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)
    def test_validacion_varios_idiomas(self):

        data = {
            'correo': 'usuario324@ejemplo.com',
            'password': '34234',
            'ciudad': 'Bogota',
            'pais': 'Colombia',
            'telefono': '43534543',
            'nombreCompleto': 'Carlos Arango',
            'edad': 34,
            'idiomas': 'Ingles',
            'rasgosPersonalidad': 'Sociable'
        }

        # Verificar si el usuario ya existe en la base de datos
        with app.app_context():
            usuario_existente = self.session.query(User).filter_by(correo=data['correo']).first()
            if usuario_existente:
                # Si el usuario existe, no se crea
                response = self.app.post('/users', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 412)  # Código 409 para indicar un conflicto
               
            else:
                # Si el usuario no existe, se crea
                response = self.app.post('/users', data=json.dumps(data), content_type='application/json')
                self.assertEqual(response.status_code, 201)

                # Se verifica que el usuario creado tiene los idiomas correctos
                nuevo_usuario = self.session.query(Candidato).filter_by(nombreCompleto=data['nombreCompleto']).first()
                self.assertIsNotNone(nuevo_usuario)
                self.assertEqual(nuevo_usuario.idiomas, data['idiomas'])
    
    def teardown_method(self, args):
        self.session.close()
        Base.metadata.drop_all(bind=engine)