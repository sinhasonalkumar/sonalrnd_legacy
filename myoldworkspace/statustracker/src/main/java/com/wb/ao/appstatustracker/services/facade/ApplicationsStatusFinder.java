package com.wb.ao.appstatustracker.services.facade;

import java.util.Map;

public interface ApplicationsStatusFinder {

	Map<String, Map<String, String>> checkHealthStatus();
}
