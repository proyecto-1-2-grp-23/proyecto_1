from flask import Flask
from flask_restful import Api
from vistas.AppLogic import HealthCheck, Prueba

app = Flask(__name__)
api = Api(app)
api.add_resource(Prueba, '/Cuestionario')
api.add_resource(HealthCheck, '/HealthCheck')

if __name__ == "__main__":
    app.run()
