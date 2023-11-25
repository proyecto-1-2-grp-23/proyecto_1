import json
from datetime import datetime

from .base_command import BaseCommannd
from ..models.project import Project, ProjectSchema, CreatedProjectJsonSchema
from ..session import Session
from flask import jsonify
class UpdateProject(BaseCommannd):
        def __init__(self, data, id):
            self.data = data
            self.id = id

        def execute(self):
            session = Session()
            try:
                project = session.query(Project).filter(Project.id == self.id).first()
                if project:
                    for key, value in self.data.items():
                        if key in ["startDate", "finishDate"] and isinstance(value, str):
                            value = datetime.strptime(value, "%Y-%m-%dT%H:%M:%S")
                        if hasattr(project, key):
                            setattr(project, key, value)
                    session.commit()
                    session.close()
                    return ({"message": "Proyecto actualizado correctamente"})
                else:
                    return {"message": "Proyecto no encontrado"}
                    
            except Exception as e:
                session.rollback()
                return {"message": f"Error al actualizar el proyecto: {str(e)}"}

            finally:
                session.close()