package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetSessionsQuery implements Command<List<Session>> { }
