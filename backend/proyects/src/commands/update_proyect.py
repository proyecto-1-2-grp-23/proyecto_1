import json
from .base_command import BaseCommannd
from ..models.project import Project, ProjectSchema, CreatedProjectJsonSchema
from ..session import Session
from flask import jsonify
class UpdateProject(BaseCommannd):
    try:
        def __init__(self, data, id):
            self.data = data
            self.id = id

        def execute(self):
            required_fields = ["nombre", "descripcion", "perfiles", "conocimientos_tecnicos", "habilidades_blandas"]
            if all(field in self.data for field in required_fields):
                session = Session()
                project = (session.query(Project).filter(Project.id == self.id).first())
                if project:
                    for key, value in (self.data).items():
                        setattr(project, key, value)
                        session.commit()
                        session.close()
                    return ({"message": "Proyecto actualizado correctamente"})
                else:
                    return {"message": "Proyecto no encontrado"}
            else:
                return {"error": "Faltan campos obligatorios"}
                    
    except Exception as error:
        # handle the exception
        print(
            "An exception occurred:", error
        )  # An exception occurred: division by zero