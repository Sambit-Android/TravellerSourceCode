<%@include file="/common/taglibs.jsp"%>

<div id="uploadDialog" title="Upload Document" >
	<kendo:upload name="file" multiple="false" 
		>
		<kendo:upload-async autoUpload="true" saveUrl="./invoice/xl/upload" />
	</kendo:upload>
</div>