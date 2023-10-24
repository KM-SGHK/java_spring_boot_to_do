import {
  getTasksSuccess,
  getTasksFailure,
  createTasksSuccess,
  createTasksFailure,
  updateTasksSuccess,
  updateTasksFailure,
  deleteTasksSuccess,
  deleteTasksFailure
} from "./action";

// @GET tasks
export function fetchTasks() {
  return async (dispatch) => {
    const res = await fetch("http://localhost:8080/toDo/getAllToDos", {
      method: "GET",
    });

    // to be refactored
    if (res.status === 200) {
      let data = await res.json();
      dispatch(getTasksSuccess(formatAllTasks(data)));
    }

    if (res.status !== 200) {
      dispatch(getTasksFailure("Failed to get tasks via api"));
    }
  };
}

// @POST tasks
export function postTasks(payload) {
  return async (dispatch) => {
    const res = await fetch("http://127.0.0.1:8080/toDo/createToDo", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    // to be refactored
    if (res.status === 200) {
      const data = await res.json();
      console.log("checking payload in post case, ", data);
      dispatch(createTasksSuccess(formatTaskPayload(data)));
    }

    if (res.status !== 200) {
      dispatch(createTasksFailure("Failed to create tasks via api"));
    }
  };
}

// @PUT tasks
export function updateTasks(id, updateData) {
  return async (dispatch) => {
    console.log("checking updateData in put case, ", updateData)
    const res = await fetch(`http://127.0.0.1:8080/toDo/updateToDo`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updateData),
    });

    // to be refactored
    if (res.status === 200) {
        const data = await res.json();
        console.log("checking payload in put case, ", data);
        dispatch(updateTasksSuccess(formatTaskPayload(data)));
      }
  
      if (res.status !== 200) {
        dispatch(updateTasksFailure("Failed to edit tasks via api"));
      }
  };
}


// @DELETE tasks
export function deleteTasks(id) {
    return async (dispatch) => {
      const res = await fetch(`http://127.0.0.1:8080/toDo/deleteToDo?id=${id}`, {
        method: "DELETE"
      });
  
      // to be refactored
      if (res.status === 200) {
          console.log("checking payload in delete case, ", id);
          dispatch(deleteTasksSuccess(id));
        }
    
        if (res.status !== 200) {
          dispatch(deleteTasksFailure("Failed to delete tasks via api"));
        }
    };
  }

function formatAllTasks(data) {
    let allEditedTasks = []
    for(let task of data){
        allEditedTasks.push(formatTaskPayload(task))
    }
    return allEditedTasks
}


function formatTaskPayload(data) {
    let task = {}
    task["id"] = data.taskId
    task["task_title"] = data.taskTitle
    task["task_body"] = data.taskBody
    return task
}
