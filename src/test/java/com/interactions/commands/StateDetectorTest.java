package com.interactions.commands;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StateDetectorTest {

    private StateDetector stateDetector;

    @Before
    public void before() {
        stateDetector = new StateDetector();
    }

    @Test
    public void shouldReturnStateProcessingWhenOneOfTheSubtaskInProcessing() {
        Task processingTask1 = buildEventInState(State.ST_PROCESSING);
        Task processingTask2 = buildEventInState(State.ST_PROCESSING);
        Task doneTask = buildEventInState(State.ST_DONE);

        List<Task> tasks = Lists.newArrayList(processingTask1, processingTask2, doneTask);

        assertThat(stateDetector.detect(tasks), is(State.ST_PROCESSING));
    }

    @Test
    public void shouldReturnStateFailedWhenOneOfTheSubtaskInFailed() {
        Task processingTask1 = buildEventInState(State.ST_FAILED);
        Task processingTask2 = buildEventInState(State.ST_PROCESSING);
        Task doneTask = buildEventInState(State.ST_DONE);

        List<Task> tasks = Lists.newArrayList(processingTask1, processingTask2, doneTask);

        assertThat(stateDetector.detect(tasks), is(State.ST_FAILED));
    }

    @Test
    public void shouldReturnStateDoneWhenAllOfTheSubtaskInDone() {
        Task processingTask1 = buildEventInState(State.ST_DONE);
        Task processingTask2 = buildEventInState(State.ST_DONE);
        Task doneTask = buildEventInState(State.ST_DONE);

        List<Task> tasks = Lists.newArrayList(processingTask1, processingTask2, doneTask);

        assertThat(stateDetector.detect(tasks), is(State.ST_DONE));
    }

    @Test
    public void shouldReturnStateFailedWhenOneOfTheSubtaskHasFailed() {
        Task processingTask1 = buildEventInState(State.ST_DONE);
        Task processingTask2 = buildEventInState(State.ST_FAILED);
        Task doneTask = buildEventInState(State.ST_DONE);

        List<Task> tasks = Lists.newArrayList(processingTask1, processingTask2, doneTask);

        assertThat(stateDetector.detect(tasks), is(State.ST_FAILED));
    }

    @Test
    public void shouldReturnStateProcessingWhenOneOfTheSubtaskInNew() {
        Task processingTask1 = buildEventInState(State.ST_NEW);
        Task doneTask = buildEventInState(State.ST_DONE);

        List<Task> tasks = Lists.newArrayList(processingTask1, doneTask);

        assertThat(stateDetector.detect(tasks), is(State.ST_PROCESSING));
    }


    private Task buildEventInState(State state) {
        Task task = new TaskBuilder().withId(1).withState(state).build();
        return task;
    }
}
